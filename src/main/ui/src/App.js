import logo from './logo.svg';
import './App.css';
import Board from './components/Board';

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <p>
                    Time to Code Some Chess Stuff
                </p>
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Learn React
                </a>
                <Board/>
            </header>
        </div>
    );
}

export default App;
