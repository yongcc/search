<template>
  <div>
    <h2>login</h2>
    <form @submit.prevent="onSubmit(id, password)">
      <input type="text" v-model="id" placeholder="ID">
      <input type="password" v-model="password" placeholder="Password">
      <input type="submit" value="Login">
    </form>
    <div>
      <h2>{{message}}</h2>
    </div>
    <div>
      <h2 v-on:click="signUp">[sign up]</h2>
    </div>
    <div>
      <router-view/>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      id: '',
      password: '',
      message: ''
    }
  },
  created () {
    axios.get('/user/id').then((data) => {
      this.$router.push('/search')
    })
  },
  methods: {
    onSubmit (id, password) {
      this.message = ''
      this.$store.dispatch('LOGIN', {id, password})
        // .then(() => this.redirect())
        .then(() => this.$router.push('/search'))
        .catch(({message}) => (this.message = '로그인에 실패했습니다. 아이디와 비밀번호를 확인하세요.'))
    },
    redirect () {
      const {search} = window.location
      const tokens = search.replace(/^\?/, '').split('&')
      const {returnPath} = tokens.reduce((qs, tkn) => {
        const pair = tkn.split('=')
        qs[pair[0]] = decodeURIComponent(pair[1])
        return qs
      }, {})

      // 리다이렉트 처리
      this.$router.push(returnPath)
    },
    signUp () {
      this.$router.push('/signUp')
    }
  }
}
</script>
