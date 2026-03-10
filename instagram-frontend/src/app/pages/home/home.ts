import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

  posts = [
    {
      username: 'marko',
      avatar: 'https://i.pravatar.cc/40?img=1',
      image: 'https://picsum.photos/500/400',
      caption: 'Enjoying the view!'
    },
    {
      username: 'ana',
      avatar: 'https://i.pravatar.cc/40?img=5',
      image: 'https://picsum.photos/500/401',
      caption: 'Beautiful day!'
    },
    {
      username: 'nikola',
      avatar: 'https://i.pravatar.cc/40?img=8',
      image: 'https://picsum.photos/500/402',
      caption: 'Weekend trip!'
    },
    {
      username: 'mila',
      avatar: 'https://i.pravatar.cc/40?img=7',
      image: 'https://picsum.photos/500/403',
      caption: 'Coffee time ☕'
    },
    {
      username: 'petar',
      avatar: 'https://i.pravatar.cc/40?img=6',
      image: 'https://picsum.photos/500/404',
      caption: 'Sunset vibes 🌅'
    },
    {
      username: 'jana',
      avatar: 'https://i.pravatar.cc/40?img=9',
      image: 'https://picsum.photos/500/405',
      caption: 'Travel memories ✈️'
    },
    {
      username: 'ivan',
      avatar: 'https://i.pravatar.cc/40?img=4',
      image: 'https://picsum.photos/500/406',
      caption: 'City lights tonight 🌃'
    }
  ];

}
