<template>
  <div>
    <h2>Home</h2>
    <div>{{greeting}}</div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Home',
  data() {
    return {
      greeting: ''
    }
  },
  created() {
    axios.get('/home')
      .then(result => this.greeting = result.data.greeting)
  }
}
</script>