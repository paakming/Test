import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Manage from "@/views/Manage";
import User from "@/views/User";
import Home from "@/views/Home";
import Doctor from "@/views/Doctor";
import Patient from "@/views/Patient";
import Register from "@/views/Register";
import MyInfo from "@/views/MyInfo";
import Staff from "@/views/Staff";
import ChangePwd from "@/views/ChangePwd";
import PatientManage from "@/views/PatientManage";
import Prescription from "@/views/Prescription";
import Registration from "@/views/Registration";
import Medicine from "@/views/Medicine";
import Finance from "@/views/Finance";
import DoctorHome from "@/views/DoctorHome";
import PatientList from "@/views/PatientList";
import PatientMain from "@/views/PatientMain";
import SeePatient from "@/views/SeePatient";

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/register',
    name:'register',
    component: Register
  },
  {
    path: '/doctor',
    name: 'doctor',
    component: Doctor,
    children: [
       {
           path: 'myinfo',
           name:'myInfo',
           component: MyInfo
       },
        {
            path: 'doctorhome',
            name:'doctorhome',
            component: DoctorHome
        },
        {
            path: 'seepatient',
            name:'seepatient',
            component: SeePatient,
            children: [
                {
                    path: 'patientlist',
                    name:'patientlist',
                    component: PatientList,
                },
                {
                    path: 'patientmain',
                    name:'patientmain',
                    component: PatientMain,
                }
            ]
        },
    ]
  },
  {
    path: '/patient',
    name: 'patient',
    component: Patient
  },
  {
    path: '/manage',
    name: 'manage',
    component: Manage,
    children:[
        {
          path:'user',
          name:'user',
          component: User
        },
        {
          path: 'home',
          name: 'home',
          component: Home
         },
        {
          path: 'myinfo',
          name:'myinfo',
          component: MyInfo
        },
        {
          path: 'changepwd',
          name: 'changepwd',
          component: ChangePwd
        },
        {
          path: 'staff',
          name:'staff',
          component: Staff
        },
        {
          path: 'patientManage',
          name: 'patientManage',
          component: PatientManage
        },
        {
          path: 'prescription',
          name: 'prescription',
          component: Prescription
        },
        {
          path: 'registration',
          name: 'registration',
          component: Registration
        },
        {
          path: 'medicine',
          name: 'medicine',
          component: Medicine
        },
        {
          path: 'finance',
          name: 'finance',
          component: Finance
        }
    ]
  }
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router
