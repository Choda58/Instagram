import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { RouterModule, Router } from '@angular/router';

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

  constructor(private userService: UserService, private router: Router) {}

  login(): void {

    const data = {
      username: this.username,
      password: this.password
    };

    this.userService.login(data).subscribe({
      next: (response) => {

        console.log("Login success", response);

        // čuvamo usera
        localStorage.setItem("user", JSON.stringify(response));

        // redirect na home
        this.router.navigate(['/home']);
      },

      error: (err) => {
        console.error("Login error", err);
      }

    });

  }

}
