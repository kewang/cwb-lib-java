package tw.kewang.cwb;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TokenInterceptor implements Interceptor {
    private Cwb cwb;

    public TokenInterceptor(Cwb cwb) {
        this.cwb = cwb;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request req = chain.request();

        return chain.proceed(req.newBuilder().header("Authorization", cwb.getApiKey()).build());
    }
}
