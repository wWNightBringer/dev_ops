import React from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom"
import Header from './components/Header'
import Footer from './components/Footer'
import Home from './Views/Home'
import FirstService from './Views/FirstService'
import SecondService from './Views/SecondService'

function App() {

    return (
        <div className="App">
            <Router>
                <Header/>

                <div className="p-3">
                    <Switch>
                        <Route exact path="/">
                            <Home/>
                        </Route>
                        <Route path="/first-service">
                            <FirstService/>
                        </Route>
                        <Route path="/second-service">
                            <SecondService/>
                        </Route>
                    </Switch>
                </div>

                <Footer/>
            </Router>
        </div>
    );
}

export default App;
