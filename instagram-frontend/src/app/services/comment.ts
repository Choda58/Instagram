import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private apiUrl = "http://localhost:8083";

  constructor(private http: HttpClient) {}

  getComments(postId: number) {
    return this.http.get(`${this.apiUrl}/comments/${postId}`);
  }

  addComment(postId: number, userId: number, text: string) {

    const body = {
      text: text,
      postId: postId,
      userId: userId
    };

    return this.http.post(`${this.apiUrl}/comments`, body);
  }

  deleteComment(commentId: number) {
    return this.http.delete(`http://localhost:8083/comments/${commentId}`);
  }

  updateComment(commentId: number, text: string) {
    return this.http.put(`${this.apiUrl}/comments/${commentId}`, text);
  }

}
