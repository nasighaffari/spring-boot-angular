import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {User} from "../models/user.model";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss']
})
export class EditUserComponent implements OnInit {

  id: number;
  user: User = new User();

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.userService.findUserById(this.id).subscribe(data => this.user = data);
  }

  editUser(): void {
    this.userService.updateUser(this.user)
      .subscribe(data => {
        alert("User edited successfully.")
      });
  };

}
