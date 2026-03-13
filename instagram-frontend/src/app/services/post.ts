import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private apiUrl = "http://localhost:8083";

  constructor(private http: HttpClient) {}

  getPostsByUser(userId: number) {
    return this.http.get(`${this.apiUrl}/posts/user/${userId}`);
  }

  createPost(userId: number, description: string, files: File[]) {

    const formData = new FormData();

    formData.append("userId", userId.toString());
    formData.append("description", description);

    for (let file of files) {
      formData.append("files", file);
    }

    return this.http.post(`${this.apiUrl}/posts`, formData);
  }

  likePost(postId: number, userId: number) {
    return this.http.post(
      `${this.apiUrl}/likes?userId=${userId}&postId=${postId}`,
      null
    );
  }

  unlikePost(postId: number, userId: number) {
    return this.http.delete(
      `${this.apiUrl}/likes?userId=${userId}&postId=${postId}`
    );
  }

  getLikesCount(postId: number) {
    return this.http.get(`${this.apiUrl}/likes/count/${postId}`);
  }

}
