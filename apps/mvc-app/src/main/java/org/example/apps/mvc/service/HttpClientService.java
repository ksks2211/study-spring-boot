package org.example.apps.mvc.service;

import org.example.apps.mvc.dto.PostDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author rival
 * @since 2023-10-24
 */

@Service
@RequiredArgsConstructor
public class HttpClientService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper;
    private final String URL = "http://localhost:8888/posts";


    public PostDto getPost(Long id) throws IOException {
        Request request = new Request.Builder()
            .url(URL+"/"+id)
            .build();

        try(Response response = client.newCall(request).execute()){
            if(!response.isSuccessful()){
                throw new IOException("Unexpected code "+response);
            }
            if(response.body()!=null){
                return objectMapper.readValue(response.body().byteStream(),PostDto.class);
            }else{
                throw new IOException("Response body is null");
            }
        }
    }







}
