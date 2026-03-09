import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  username: string = '';
  password: string = '';

  constructor(private userService: UserService) {}

  login(): void {

    const data = {
      username: this.username,
      password: this.password
    };

    this.userService.login(data).subscribe(response => {
      console.log('Login response:', response);
    });

  }

}
