import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.html'
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

    this.userService.login(data).subscribe((response: any) => {

      console.log(response);

      this.router.navigate(['/timeline']);

    });

  }

}
