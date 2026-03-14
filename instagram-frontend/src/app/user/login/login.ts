import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UserService } from '../../services/user';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  username: string = '';
  password: string = '';

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  login(): void {

    const data = {
      username: this.username,
      password: this.password
    };

    this.userService.login(data).subscribe({

      next: (res: any) => {

        console.log(res);

        // čuvamo userId za ostatak aplikacije
        localStorage.setItem("userId", res.id);

        // idemo na timeline
        this.router.navigate(['/timeline']);

      },

      error: () => {
        alert("Invalid username or password");
      }

    });

  }

}
