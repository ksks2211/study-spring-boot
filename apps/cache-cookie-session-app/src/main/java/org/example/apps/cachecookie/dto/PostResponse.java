package org.example.apps.cachecookie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author rival
 * @since 2024-02-15
 */

@RedisHash("Post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 123;

    @Id
    private String id;
    private String title;
    private String content;
}
