package pri.sungjin.getgithub.api

import pri.sungjin.getgithub.api.repository.GitRepositoriesResult
import pri.sungjin.getgithub.api.repository.GitRepositoriesResultItem
import pri.sungjin.getgithub.api.user.GitUserResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubAPI {
    companion object {
        val BASE_URL = "https://api.github.com/"
    }
    @GET("users/{userid}")
    fun getUserData(@Path("userid") id: String): Call<GitUserResult>

    @GET("users/{userid}/repos")
    fun getUserRepositories(@Path("userid") id: String): Call<GitRepositoriesResult>

    @GET("repos/{userid}/{repository}")
    fun getRepository(@Path("userid") id: String
                      ,@Path("repository") name: String) : Call<GitRepositoriesResultItem>
}