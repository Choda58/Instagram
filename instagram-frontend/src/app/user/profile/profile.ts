import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { UserService } from '../../services/user';
import { PostService } from '../../services/post';
import { FollowService } from '../../services/follow';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './profile.html',
  styleUrl: './profile.css'
})
export class Profile implements OnInit {

  user: any;
  userId!: number;

  posts: any[] = [];

  followersCount = 0;
  followingCount = 0;

  isFollowingUser = false;

  description: string = "";
  selectedFiles: File[] = [];

  loggedUserId = 1;

  constructor(
    public userService: UserService,
    private postService: PostService,
    private followService: FollowService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {

    this.userId = Number(this.route.snapshot.paramMap.get('id'));

    this.userService.getUserById(this.userId)
      .subscribe((data: any) => {
        this.user = data;
      });

    this.loadPosts();

    this.followService.getFollowers(this.userId)
      .subscribe((data: any) => {
        this.followersCount = data.length;
      });

    this.followService.getFollowing(this.userId)
      .subscribe((data: any) => {
        this.followingCount = data.length;
      });

    this.followService.isFollowing(this.loggedUserId, this.userId)
      .subscribe((data: any) => {
        this.isFollowingUser = data;
      });

  }

  loadPosts() {

    this.postService.getPostsByUser(this.userId)
      .subscribe((data: any) => {

        let mergedPosts: any[] = [];
        let currentPost: any = null;

        for (let p of data) {

          if (p.description && p.description.trim() !== "") {

            currentPost = { ...p };
            mergedPosts.push(currentPost);

          }
          else if (p.media && p.media.length > 0 && currentPost) {

            currentPost.media = p.media;

          }

        }

        this.posts = mergedPosts;

      });

  }

  onFileSelected(event: any) {

    this.selectedFiles = [];

    for (let file of event.target.files) {
      this.selectedFiles.push(file);
    }

  }

  createPost() {

    this.postService.createPost(this.userId, this.description, this.selectedFiles)
      .subscribe(() => {

        this.loadPosts();

        this.description = "";
        this.selectedFiles = [];

      });

  }

  follow(){

    this.followService.follow(this.loggedUserId, this.userId)
      .subscribe(() => {

        this.isFollowingUser = true;
        this.followersCount++;

      });

  }

  unfollow(){

    this.followService.unfollow(this.loggedUserId, this.userId)
      .subscribe(() => {

        this.isFollowingUser = false;
        this.followersCount--;

      });

  }

}
