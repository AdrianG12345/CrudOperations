import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";

const CreateGrupContainer = () => {
    const [data, setData] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const history = useNavigate();

    const [inputValue, setInputValue] = useState('');

    const handleInputChange = (e) => {
        setInputValue(e.target.value);
    };


    const createData = async () => {
        const token = localStorage.getItem('jwtToken'); // Retrieve the token from localStorage

        if (!token) {
            setError('No token found');
            setIsLoading(false);
            return;
        }

        try {
            const parsedInt = parseInt(inputValue, 10);

            const response = await fetch(`http://localhost:8080/grup/add`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({
                    groupNr : parsedInt
                })
            });

            setData(response.data);
            setIsLoading(false);
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
            <h2>Create</h2>
            <input
                type="number"
                value={inputValue}
                onChange={handleInputChange}
                placeholder="Enter an integer"
            />
            <button onClick={createData}>Create Data</button> {/* Button to fetch data */}
            <button onClick={goBack}>Go Back</button> {/* Button to fetch data */}
        </div>
    );
};

export default CreateGrupContainer;
