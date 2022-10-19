package in.payhere.pos.financial.security.jwt;


public interface JwtProperties {
    String SECRET = "payhere"; // 우리 서버만 알고 있는 비밀값
    int EXPIRATION_TIME = 600000; // 10분
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
