<template>
  <div>
    <router-link to="/">Home</router-link>
    <a href="" v-if="isAuthenticated" @click.prevent="onClickLogout">Logout</a>
    <router-link to="/login" v-else>Login</router-link>
    <router-link to="/me">Me</router-link>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Menus',
  computed: {
    isAuthenticated() {
      return this.$store.getters.isAuthenticated
    }
  },
  methods: {
    onClickLogout() {
      // LOGOUT 변이 실행 후 리다이렉트
      store.dispatch('LOGOUT').then(() => this.$router.push('/'))
    }
  }
}
</script>
