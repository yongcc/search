<template>
  <div>
    <h2>Me</h2>
    <div>
      <label>User Info:</label>
      <pre>{{user}}</pre>
    </div>
    <div>
      <label>Access Log:</label>
      <div v-for="log in accessLog" v-bind:key="log.userId">{{log.userId}}, {{log.createdAt}}</div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Me',
  data () {
    return {
      user: null,
      accessLog: []
    }
  },
  created () {
    axios.get('/me')
      .then(({data}) => {
        this.user = data.user
        this.accessLog = data.accessLog
      })
  }
}
</script>
