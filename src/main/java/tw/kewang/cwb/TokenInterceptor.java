package tw.kewang.cwb;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request req = chain.request();

        return chain.proceed(req.newBuilder().header("Authorization", Cwb.getApiKey()).build());
    }
}
