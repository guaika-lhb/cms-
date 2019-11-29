package edy.ynmd.cms.tools;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final Logger logger= LoggerFactory.getLogger(JwtUtil.class);
    public static  final long EXPIRATION_TIME=7200_00;//6分钟
    public static final String SECRET="admin123";
    public static final String TOKEN_PREFIX="Bearer";
    public static final String HEADER_STRING="Authorization";
    public static final String ROLE="ROLE";


    public static String generateToken(String userRole,String userid){
        HashMap<String,Object> map=new HashMap<>();
        map.put(ROLE,userRole);
        map.put("userid",userid);
        String jwt= Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
        return TOKEN_PREFIX+" "+jwt;
    }

    public static String generateToken(String userRole,String userid,long exprationtime){
        HashMap<String,Object> map=new HashMap<>();
        map.put(ROLE,userRole);
        map.put("userid",userid);
        String jwt= Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis()+exprationtime))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
        return TOKEN_PREFIX+" "+jwt;
    }


    public static Map<String,Object> validateTokenAndGetClaims(HttpServletRequest request){
        String token=request.getHeader(HEADER_STRING);
        if(token==null){
            throw new TokenValidationException("Messing Token");

        }
        else{
            Map<String,Object> body=Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                    .getBody();
            return body;
        }
    }




    static class TokenValidationException extends RuntimeException{
        public TokenValidationException(String msg){
            super(msg);
        }
    }




}