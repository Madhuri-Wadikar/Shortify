import { createContext, useContext, useState } from "react";

const ContextApi = createContext();

// export const ContextProvider = ({ children }) => {
//     const getToken = localStorage.getItem("JWT_TOKEN")
//         ? JSON.parse(localStorage.getItem("JWT_TOKEN"))
//         : null;

//     const [token, setToken] = useState(getToken);

//     const sendData = {
//         token,
//         setToken,
//     };

//     return <ContextApi.Provider value={sendData}>{children}</ContextApi.Provider>
// };
export const ContextProvider = ({ children }) => {
    // 1. Get the token safely (Check if it's a valid string first)
    const getInitialToken = () => {
        const localData = localStorage.getItem("JWT_TOKEN");
        if (!localData) return null;
        try {
            // Only use JSON.parse if you used JSON.stringify to save it
            return JSON.parse(localData);
        } catch (e) {
            // If it's just a raw string, return it as is
            return localData;
        }
    };

    const [token, setToken] = useState(getInitialToken());

    const sendData = {
        token,
        setToken,
    };

    return <ContextApi.Provider value={sendData}>{children}</ContextApi.Provider>
};


export const useStoreContext = () => {
    const context = useContext(ContextApi);
    return context;
}