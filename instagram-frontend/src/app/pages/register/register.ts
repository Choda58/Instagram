import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css'],
})
export class Register {

  username: string = '';
  email: string = '';
  password: string = '';

  constructor(private userService: UserService) {}

  register(): void {

    const user = {
      username: this.username,
      email: this.email,
      password: this.password
    };

    this.userService.register(user).subscribe(response => {
      console.log('Register response:', response);
    });

  }

}
