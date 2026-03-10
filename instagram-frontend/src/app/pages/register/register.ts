import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { RouterModule, Router } from '@angular/router';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css'],
})
export class Register {

  name: string = '';
  username: string = '';
  email: string = '';
  password: string = '';

  constructor(private userService: UserService, private router: Router) {}

  register(): void {

    const user = {
      name: this.name,
      username: this.username,
      email: this.email,
      password: this.password,
      bio: '',
      profilePicture: '',
      isPrivateProfile: false
    };

    this.userService.register(user).subscribe({
      next: (response) => {
        console.log('Register success:', response);
        this.router.navigate(['/login']);   // redirect na login
      },
      error: (err) => {
        console.error('Register error:', err);
      }
    });

  }

}
