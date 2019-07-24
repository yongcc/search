<template>
  <div class="mainList">
    <div class="list">
      <h2>인기 키워드 목록</h2>
      <div>
        <ul>
          <li v-for="keyword in keywords" :key="keyword.keyword" v-on:click="goSearch(keyword.keyword)">
            {{keyword.keyword}} - {{keyword.count}}회
          </li>
        </ul>
      </div>
    </div>
    <div class="list">
      <h2>내 검색 히스토리</h2>
      <div>
        <ul>
          <li v-for="recent in recents" :key="recent.keyword" v-on:click="goSearch(recent.keyword)">
            {{recent.keyword}} - {{recent.date}}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      keywords: '',
      recents: ''
    }
  },
  created () {
    axios.get('/keyword/book').then(({data}) => {
      this.keywords = data
    }).catch(() => {
      this.$router.push('/')
    })
    axios.get('/recent/book').then(({data}) => {
      this.recents = data
    }).catch(() => {
      this.$router.push('/')
    })
  },
  methods: {
    goSearch (query) {
      this.$store.state.searchBookParam = {query}
      this.$router.push('/search/book/progress')
    }
  }
}
</script>

<style>
.mainList .list {
    width: 50%;
    float: left;
    list-style: none;
    padding-left: 0;
}
.mainList li {
    margin-bottom: 20px;
}
.mainList .list ul {padding-left: 0;}
.mainList .list li {list-style: none;}
</style>
