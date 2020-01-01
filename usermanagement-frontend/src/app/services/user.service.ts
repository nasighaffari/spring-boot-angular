import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {User} from '../models/user.model';
import {Observable} from "rxjs";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class UserService {

  private userUrl = '/api/users';

  constructor(private http: HttpClient) {
  }

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.userUrl);
  }

  public findUserById(id: number): Observable<any> {
    return this.http.get(this.userUrl + "/" + id);
  }

  public deleteUser(user) {
    return this.http.delete(this.userUrl + "/" + user.id);
  }

  public createUser(user) {
    return this.http.post<User>(this.userUrl, user);
  }

  public updateUser(user) {
    console.log("user: " + user.id + " " + user.firstName + " rtrtrtrtr" + user.lastName);
    this.http.post<User>(this.userUrl, user);
    console.log("user: " + user.id + " " + user.firstName + " uuuuuuuuuuuuu" + user.lastName);
    return this.http.put(this.userUrl, user, httpOptions);
  }

}
