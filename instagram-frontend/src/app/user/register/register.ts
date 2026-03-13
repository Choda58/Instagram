import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.html'
})
export class Register {

  username: string = '';
  name: string = '';
  email: string = '';
  password: string = '';

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  register(): void {

    const data = {
      username: this.username,
      name: this.name,
      email: this.email,
      password: this.password,
      bio: "",
      profilePicture: "",
      isPrivateProfile: false
    };

    this.userService.register(data).subscribe({
      next: (response) => {

        console.log(response);

        alert("User registered!");

        this.router.navigate(['/login']);
      },

      error: (err) => {
        console.error(err);
        alert("Registration failed");
      }
    });

  }

}
