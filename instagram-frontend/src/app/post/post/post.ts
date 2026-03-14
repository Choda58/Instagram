import { Component, Input, OnChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostService } from '../../services/post';
import { CommentService } from '../../services/comment';
import { FormsModule } from '@angular/forms';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-post',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './post.html',
  styleUrls: ['./post.css']
})
export class Post implements OnChanges {

  @Input() post: any;

  likesCount: number = 0;
  liked: boolean = false;

  comments: any[] = [];
  commentText: string = "";

  editingCommentId: number | null = null;
  editedText: string = "";

  loggedUserId = Number(localStorage.getItem("userId"));

  constructor(
    private postService: PostService,
    private commentService: CommentService
  ) {}

  ngOnChanges(): void {
    if (this.post) {
      this.loadLikes();
      this.loadComments();
    }
  }

  loadLikes() {
    this.postService.getLikesCount(this.post.id)
      .subscribe((count: any) => {
        this.likesCount = count;
      });
  }

  toggleLike() {

    const userId = this.loggedUserId;

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

  loadComments() {

    this.commentService.getComments(this.post.id)
      .subscribe((data: any) => {
        this.comments = data;
      });

  }

  addComment() {

    const text = this.commentText.trim();

    if (!text) return;

    this.commentText = "";

    this.commentService
      .addComment(this.post.id, this.loggedUserId, text)
      .subscribe((comment: any) => {

        this.comments.push(comment);

      });

  }

  deleteComment(commentId: number) {

    this.commentService.deleteComment(commentId)
      .subscribe(() => {

        this.comments = this.comments.filter(c => c.id !== commentId);

      });

  }

  startEdit(comment: any) {

    this.editingCommentId = comment.id;
    this.editedText = comment.text;

  }

  saveEdit(comment: any) {

    const text = this.editedText.trim();

    if (!text) {
      this.editingCommentId = null;
      return;
    }

    this.commentService.updateComment(comment.id, text)
      .subscribe(() => {

        comment.text = text;
        this.editedText = "";
        this.editingCommentId = null;

      });

  }
  showComments = false;

  toggleComments(){
    this.showComments = !this.showComments;
  }

}
