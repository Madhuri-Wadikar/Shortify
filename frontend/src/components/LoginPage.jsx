import React, { useState } from 'react'
import { useForm } from 'react-hook-form'
import TextField from './TextField';
import { Link, useNavigate } from 'react-router-dom';
import api from '../api/api';
import toast from 'react-hot-toast';
import { useStoreContext } from '../contextApi/ContextApi';

const LoginPage = () => {
    const navigate = useNavigate();
    const [loader, setLoader] = useState(false);
    const { setToken } = useStoreContext();

    const {
        register,
        handleSubmit,
        reset,
        formState: {errors}
    } = useForm({
        defaultValues: {
            username: "",
            email: "",
            password: "",
        },
        mode: "onTouched",
    });

    const loginHandler = async (data) => {
        setLoader(true);
        try {
            // Only send username and password
            const loginPayload = {
                username: data.username,
                password: data.password
            };
    
            const { data: response } = await api.post("/api/auth/public/login", loginPayload);
            
            setToken(response.token);
            // localStorage.setItem("JWT_TOKEN", JSON.stringify(response.token));
            localStorage.setItem("JWT_TOKEN", response.token); // No JSON.stringify for raw strings
            toast.success("Login Successful!");
            reset();
            navigate("/dashboard");
        } catch (error) {
            console.error("Login Error:", error.response?.data || error.message);
            toast.error(error.response?.data?.message || "Login Failed!");
        } finally {
            setLoader(false);
        }
    };

    // const loginHandler = async (data) => {
    //     setLoader(true);
    //     try {
    //         console.log("📡 Sending API request...");

    //         const { data: response } = await api.post("/api/auth/public/login",data);
    //         // console.log("Payload:", data);
    //         console.log("✅ RESPONSE:", response);
    //         console.log(response.token);
    //         setToken(response.token);
    //         localStorage.setItem("JWT_TOKEN", JSON.stringify(response.token));
    //         toast.success("Login Successful!");
    //         reset();
    //         navigate("/dashboard");
    //     } catch (error) {
    //         console.log(error);
    //         toast.error("Login Failed!")
    //     } finally {
    //         setLoader(false);
    //     }
    // };

  return (
    <div
        className='min-h-[calc(100vh-64px)] flex justify-center items-center'>
        <form onSubmit={handleSubmit(loginHandler)}
            // className="sm:w-[450px] w-[360px]  shadow-custom py-8 sm:px-8 px-4 rounded-md">
            className="sm:w-[450px] w-[360px] bg-white/90 backdrop-blur-md shadow-2xl py-8 sm:px-8 px-4 rounded-lg border border-white/20">
            <h1 className="text-center font-serif text-btnColor font-bold lg:text-3xl text-2xl">
                Login Here
            </h1>

            <hr className='mt-2 mb-5 text-black'/>

            <div className="flex flex-col gap-3">
                <TextField
                    label="UserName"
                    required
                    id="username"
                    type="text"
                    message="*Username is required"
                    placeholder="Type your username"
                    register={register}
                    errors={errors}
                />

                <TextField
                    label="Password"
                    required
                    id="password"
                    type="password"
                    message="*Password is required"
                    placeholder="Type your password"
                    register={register}
                    min={6}
                    errors={errors}
                />
            </div>

            <button
                disabled={loader}
                type='submit'
                // className='bg-customRed font-semibold text-white  bg-custom-gradient w-full py-2 hover:text-slate-400 transition-colors duration-100 rounded-sm my-3'>
                className='bg-gradient-to-r from-blue-500 to-purple-600 font-semibold text-white w-full py-2 hover:scale-105 transition-transform duration-200 rounded-sm my-3'>
                {loader ? "Loading..." : "Login"}
            </button>

            <p className='text-center text-sm text-slate-700 mt-6'>
                Don't have an account? 
                <Link
                    className='font-semibold underline hover:text-black'
                    to="/register">
                        <span className='text-btnColor'> SignUp</span>
                </Link>
            </p>
        </form>
    </div>
  )
}

export default LoginPage