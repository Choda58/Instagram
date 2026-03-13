import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FollowService {

  private apiUrl = 'http://localhost:8082/api/follows';

  constructor(private http: HttpClient) {}

  follow(followerId: number, followingId: number) {
    return this.http.post(`${this.apiUrl}/follow`, null, {
      params: {
        followerId: followerId,
        followingId: followingId
      }
    });
  }

  unfollow(followerId: number, followingId: number) {
    return this.http.delete(`${this.apiUrl}/unfollow`, {
      params: {
        followerId: followerId,
        followingId: followingId
      }
    });
  }

  isFollowing(followerId: number, followingId: number) {
    return this.http.get<boolean>(
      `${this.apiUrl}/status?followerId=${followerId}&followingId=${followingId}`
    );
  }

  getFollowers(userId: number) {
    return this.http.get(`${this.apiUrl}/followers/${userId}`);
  }

  getFollowing(userId: number) {
    return this.http.get(`${this.apiUrl}/following/${userId}`);
  }

}
