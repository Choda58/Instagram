import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FollowService {

  private apiUrl = 'http://localhost:8083/api/follow';

  constructor(private http: HttpClient) {}

  follow(data:any){
    return this.http.post(this.apiUrl,data);
  }

}
