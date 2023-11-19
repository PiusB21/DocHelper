import router from "./router"
import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import './style.css'

const app=createApp(App)
app.use(router)
app.mount('#app')
