<template>
  <div>
    <form @submit.prevent="onSubmit(id, password, password2)">
      <input type="text" v-model="id" placeholder="ID">
      <input type="password" v-model="password" placeholder="Password">
      <input type="password" v-model="password2" placeholder="Check Password">
      <input type="submit" value="Sign Up">
    </form>
    <div>
      <h2>{{message}}</h2>
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
      password2: '',
      message: ''
    }
  },
  created () {
  },
  methods: {
    onSubmit (id, password, password2) {
      this.message = ''
      axios.post('/user/signUp', {id, password, password2}).then(({data}) => {
        if (data.code === '200') {
          alert('성공')
          this.$router.push('/')
        }
        this.message = data.message
      }).catch(() => {
        this.message = '알 수 없는 오류가 발생했습니다.'
      })
    }
  }
}
</script>
