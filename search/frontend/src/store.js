import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const enhanceAccessToeken = () => {
  const {accessToken} = localStorage
  if (!accessToken) return
  axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`
}
enhanceAccessToeken()

export const store = new Vuex.Store({
  state: {
    accessToken: null
  },
  getters: {

  },
  actions: {
    LOGIN ({commit}, {email, password}) {
      return axios.post('/login', {email, password})
        .then(({data}) => {
          // LOGIN 변이 실행
          commit('LOGIN', data)
          // 모든 HTTP 요청 헤더에 Authorization 을 추가한다.
          axios.defaults.headers.common['Authorization'] = `Bearer ${data.accessToken}`
        })
    },
    LOGOUT ({commit}) {
      // HTTP 요청 헤더값 제거
      axios.defaults.headers.common['Authorization'] = undefined
      commit('LOGOUT')
    }
  },
  mutations: {
    LOGIN (state, {accessToken}) {
      // 스토어에 액세스 토큰 저장
      state.accessToken = accessToken
      // 토큰을 로컬 스토리지에 저장
      localStorage.accessToken = accessToken
    },
    LOGOUT (state) {
      // 토큰 정보 삭제
      state.accessToken = null
      delete localStorage.accessToken
    }
  }
})
