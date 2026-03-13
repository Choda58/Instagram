import { Routes } from '@angular/router';

export const routes: Routes = [

    {
        path: 'login',
        loadComponent: () =>
            import('./user/login/login').then(m => m.Login)
    },

    {
        path: 'register',
        loadComponent: () =>
            import('./user/register/register').then(m => m.Register)
    },

    {
        path: 'search',
        loadComponent: () =>
            import('./user/search/search').then(m => m.Search)
    },

    {
        path: 'timeline',
        loadComponent: () =>
            import('./post/timeline/timeline').then(m => m.Timeline)
    },
    {
        path: 'profile/:id',
        loadComponent: () =>
            import('./user/profile/profile').then(m => m.Profile)
    },



];
