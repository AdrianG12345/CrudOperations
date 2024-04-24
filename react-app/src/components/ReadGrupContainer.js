import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";
import { Route, Navigate } from 'react-router-dom';

const ReadGrupContainer = () => {
    const [data, setData] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const history = useNavigate();

    const token = localStorage.getItem('jwtToken'); // Retrieve the token from localStorage


    useEffect(() => {
        if (token === "") {
            // If token is an empty string, navigate away or display an error
            // setError('No token found');
            // history('/some-other-route'); // Uncomment this line to navigate away
            history("/");
        }
    }, [token]); // Run this effect when token changes

    const fetchData = async () => {


        if (!token) {
            setError('No token found');
            setIsLoading(false);
            return;
        }

        try {
            const response = await axios.get('http://localhost:8080/grup/getAllGroups', {
                headers: {
                    Authorization: `Bearer ${token}` // Include the token in the request headers
                }
            });

            setData(response.data);
            setIsLoading(false);
            console.log(token);
        } catch (err) {
            setError(err.message);
            setIsLoading(false);
        }
    };

    const goBack = async () => {
        history('/grup');
    }
    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="container">
            <h2>Read</h2>
            <button onClick={fetchData}>Fetch Data</button> {/* Button to fetch data */}
            <button onClick={goBack}>Go Back</button> {/* Button to fetch data */}
            <ul>
                {data.map(item => (
                    <li key={item.id}>GroupId:{item.id}     GroupNr: {item.groupNr}</li>
                ))}
            </ul>
        </div>
    );

};

export default ReadGrupContainer;
