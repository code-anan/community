package com.weilong.community.provider;

import com.alibaba.fastjson.JSON;
import com.weilong.community.dto.AccessTokenDTO;
import com.weilong.community.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class GithubProvider {
    //获取getAccessToken
    public String  getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            String token = result.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据getAccessToken获取user信息
    public GithubUserDTO getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization", "token " + accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUserDTO githubUserDTO = JSON.parseObject(string, GithubUserDTO.class);
            return githubUserDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
