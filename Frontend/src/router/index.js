import {createRouter, createWebHistory} from "vue-router"
//import jwt_decode from 'jwt-decode';

import LoginView from "../views/LoginPage.vue"
import RegisterView from "../views/RegisterPage.vue"
import AccountView from "../views/DocAccount/DocAccount.vue"
import AddView from "../views/DocAccount/AddPatients.vue"
import UpdateView from "../views/DocAccount/UpdatePatient.vue"
import DeleteView from "../views/DocAccount/DeletePatient.vue"
import PatientsView from "../views/DocAccount/ViewPatients.vue"

const routes=[
    {
        name:"register",
        path:"/",
        component:RegisterView
    },
    {
        name:"login",
        path:"/login",
        component:LoginView
    },
   
    {
        name:"loggedin",
        path:"/loggedIn",
        component:AccountView,
        meta: {requiresAuth : true},
       
        children:[
            {
                name:"view",
                path:"",
                component:PatientsView,
                
            },
            {
                name:"add",
                path:"add",
                component:AddView
            },
            {
                name:'update',
                path:'update',
                component:UpdateView
            },
            {
                name:'delete',
                path:'delete',
                component:DeleteView
            }
        ]
    },

]

const router=createRouter({
    history:createWebHistory(), 
    routes
})

router.beforeEach((to, from, next) => {
    // to and from are both route objects. must call `next`.
    if(to.meta.requiresAuth && !localStorage.getItem('token')){
        //if it requires auth and theres no token
        next('/login');
        return;
    }
    console.log("requiresAuth : "+ to.meta.requiresAuth);
    console.log("has token : " + localStorage.getItem('token'));
    next()
})

export default router