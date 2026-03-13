import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './search.html'
})
export class Search {

  username: string = '';
  users: any[] = [];

  constructor(private userService: UserService,private router: Router) {}

  searchUsers() {

    this.userService.search(this.username).subscribe((data: any) => {

      console.log(data);

      this.users = data;

    });

  }
  openProfile(id: number) {
    this.router.navigate(['/profile', id]);
  }

}
