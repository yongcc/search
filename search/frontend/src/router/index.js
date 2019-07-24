import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Home from '@/components/Home'
import Login from '@/components/Login'
import SignUp from '@/components/SignUp'
import Me from '@/components/Me'
import SearchBook from '@/components/SearchBook'
import SearchBookMain from '@/components/SearchBookMain'
import SearchBookProgress from '@/components/SearchBookProgress'
import SearchBookResult from '@/components/SearchBookResult'
import SearchBookDetail from '@/components/SearchBookDetail'

Vue.use(Router)

const requireAuth = () => (from, to, next) => {
  const isAuthenticated = false
  if (isAuthenticated) return next()
  next('/login?returnPath=me')
}

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/search',
      name: 'Home',
      component: Home,
      children: [
        {
          path: 'book',
          name: 'SearchBook',
          component: SearchBook,
          children: [
            {
              path: '',
              name: 'SearchBookMain',
              component: SearchBookMain
            },
            {
              path: 'progress',
              name: 'SearchBookProgress',
              component: SearchBookProgress
            },
            {
              path: 'result',
              name: 'SearchBookResult',
              component: SearchBookResult
            },
            {
              path: 'detail',
              name: 'SearchBookDetail',
              component: SearchBookDetail
            }
          ]
        }
      ]
    },
    {
      path: '/',
      name: 'Login',
      component: Login,
      children: [
        {
          path: 'signUp',
          name: 'SignUp',
          component: SignUp
        }
      ]
    },
    {
      path: '/me',
      name: 'Me',
      component: Me,
      beforeEnter: requireAuth()
    }
  ]
})
