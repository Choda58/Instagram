import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostService } from '../../services/post';
import { Post } from '../../post/post/post';

@Component({
  selector: 'app-timeline',
  standalone: true,
  imports: [CommonModule, Post],
  templateUrl: './timeline.html',
  styleUrl: './timeline.css'
})
export class Timeline implements OnInit {

  posts: any[] = [];
  loggedUserId: number = 1;

  constructor(private postService: PostService) {}

  ngOnInit(): void {

    this.postService.getPostsByUser(this.loggedUserId)
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

        this.posts = mergedPosts.filter(p =>
          p.media && p.media.length > 0
        );

      });

  }

}
