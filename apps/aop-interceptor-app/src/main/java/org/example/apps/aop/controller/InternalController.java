package org.example.apps.aop.controller;

import org.example.apps.aop.annotation.InternalOnly;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rival
 * @since 2023-10-23
 */
@RestController
@RequestMapping("internal")
public class InternalController {

    @GetMapping("/endpoint")
    public ResponseEntity<?> endpoint(HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        if (!"127.0.0.1".equals(remoteAddr) && !"0:0:0:0:0:0:0:1".equals(remoteAddr)) { // 0:0:0:0:0:0:0:1 is the IPv6 equivalent of 127.0.0.1
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok("EP1");
    }


    @InternalOnly
    @GetMapping("/endpoint2")
    public ResponseEntity<?> endpoint2(){
        return ResponseEntity.ok("EP2");
    }
}
