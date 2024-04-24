import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";

const UpdateGrupContainer = () => {
    const [data, setData] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    const history = useNavigate();

    const [idValue, setIdValue] = useState('');
    const [nrValue, setNrValue] = useState('');

    const handleInputChange1 = (e) => {
        setIdValue(e.target.value);
    };

    const handleInputChange2 = (e) => {
        setNrValue(e.target.value);
    };


    const updateData = async () => {
        const token = localStorage.getItem('jwtToken'); // Retrieve the token from localStorage

        if (!token) {
            setError('No token found');
            setIsLoading(false);
            return;
        }

        try {
            const parsedId = parseInt(idValue, 10);
            const parsedNr = parseInt(nrValue, 10);
            const response = await fetch(`http://localhost:8080/grup/updateGroup`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({
                    groupNr : parsedNr,
                    group_id : parsedId
                })
            });

            setData(response.data);
            setIsLoading(false);
        } catch (err) {
            setError(err.message);
            setIsLoading(false);
        }
    };

    // fetchData();
    // }, []);
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
            <h2>Update</h2>
            <input
                type="number"
                value={idValue}
                onChange={handleInputChange1}
                placeholder="Enter an integer"
            />
            <input
                type="number"
                value={nrValue}
                onChange={handleInputChange2}
                placeholder="Enter an integer"
            />
            <button onClick={updateData}>Update Data</button> {/* Button to fetch data */}
            <button onClick={goBack}>Go Back</button> {/* Button to fetch data */}

        </div>
    );
};

export default UpdateGrupContainer;
