import React, {useState} from 'react';
import {BrowserRouter, Route, Switch, Link, useNavigate, Routes} from 'react-router-dom';





const GrupContainer = () => {
    const history = useNavigate();

    const fetchData = async () => {
        const token = localStorage.getItem('jwtToken'); // Retrieve the token from localStorage
        console.log(token);
        if (token !== "")
            history('/readGrup');
        else
            console.log("Token is null");
    }
    const createData = async () => {
        const token = localStorage.getItem('jwtToken'); // Retrieve the token from localStorage
        if (token !== "")
            history('/createGrup');
        else
            console.log("Token is null");
    }
    const updateData = async () => {
        const token = localStorage.getItem('jwtToken'); // Retrieve the token from localStorage
        if (token !== "")
            history('/updateGrup');
        else
            console.log("Token is null");
    }
    const deleteData = async () => {
        const token = localStorage.getItem('jwtToken'); // Retrieve the token from localStorage
        if (token !== "")
            history('/deleteGrup');
        else
            console.log("Token is null");
    }

    return (
        <div className="container">
            <button onClick={fetchData}>Read</button> {/* Button to fetch data */}
            <button onClick={deleteData}>Delete</button>
            <button onClick={updateData}>Update</button>
            <button onClick={createData}>Create</button>
        </div>
    );
};

export default GrupContainer;
