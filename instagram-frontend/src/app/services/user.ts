import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) {}

  login(data: any) {
    return this.http.post(`${this.apiUrl}/api/users/login`, data);
  }

  register(data: any) {
    return this.http.post(`${this.apiUrl}/api/users/register`, data);
  }

  search(username: string) {
    return this.http.get(`${this.apiUrl}/api/users/search?username=${username}`);
  }

  // dobavljanje korisnika po id
  getUserById(id: number) {
    return this.http.get(`${this.apiUrl}/api/users/${id}`);
  }

  // dobavljanje profilne slike
  getProfileImage(id: number) {
    return `${this.apiUrl}/api/users/profile-image/${id}`;
  }

  // update profila
  updateUser(id: number, data: any) {
    return this.http.put(`${this.apiUrl}/api/users/${id}`, data);
  }

}
