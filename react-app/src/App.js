import React from 'react';
import {BrowserRouter as Router, Route, Routes, useNavigate} from 'react-router-dom';
import LoginPage from './components/LoginPage';
import SignupPage from './components/SignUpPage';
import Dashboard from "./components/Dashboard";
import GrupContainer from "./components/GrupContainer";
import ReadGrupContainer from "./components/ReadGrupContainer";
import CreateGrupContainer from "./components/CreateGrupContainer";
import UpdateGrupContainer from "./components/UpdateGrupContainer";
import DeleteGrupContainer from "./components/DeleteGrupContainer";


function App() {
  return (
      <div className="App">
          <Router>
              <Routes>
                  <Route path="/" element={<LoginPage />} />
                  <Route path="/signup" element={<SignupPage />} />
                  <Route path="/dashboard" element={<Dashboard />} />
                  <Route path="/grup" element={<GrupContainer />} />
                  <Route path="/readGrup" element={<ReadGrupContainer />} />
                  <Route path="/createGrup" element={<CreateGrupContainer />} />
                  <Route path="/updateGrup" element={<UpdateGrupContainer />} />
                  <Route path="/deleteGrup" element={<DeleteGrupContainer />} />

              </Routes>
          </Router>
      </div>
  );
}

export default App;
