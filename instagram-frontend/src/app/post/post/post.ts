import { Component, Input, OnChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostService } from '../../services/post';

@Component({
  selector: 'app-post',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './post.html',
  styleUrl: './post.css'
})
export class Post implements OnChanges {

  @Input() post: any;

  likesCount: number = 0;
  liked: boolean = false;

  constructor(private postService: PostService) {}

  ngOnChanges(): void {

    if (this.post) {
      this.loadLikes();
    }

  }

  loadLikes() {

    this.postService.getLikesCount(this.post.id)
      .subscribe((count: any) => {
        this.likesCount = count;
      });

  }

  toggleLike() {

    const userId = Number(localStorage.getItem("userId"));

    if (!this.liked) {

      this.postService.likePost(this.post.id, userId)
        .subscribe(() => {
          this.likesCount++;
          this.liked = true;
        });

    } else {

      this.postService.unlikePost(this.post.id, userId)
        .subscribe(() => {
          this.likesCount--;
          this.liked = false;
        });

    }

  }

}
