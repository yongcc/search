<template>
  <div>
    <div>
      <h2 v-on:click="logout()">[logout]</h2>
    </div>
    <div>
      <router-view/>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Home',
  data () {
    return {
      greeting: ''
    }
  },
  created () {
    axios.get('/user/id').then((data) => {
      this.$router.push('/search/book')
    }).catch(() => {
      this.$router.push('/')
    })
  },
  methods: {
    logout () {
      this.$store.dispatch('LOGOUT')
      this.$router.push('/')
    }
  }
}
</script>
