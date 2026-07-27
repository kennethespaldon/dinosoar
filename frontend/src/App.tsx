
function App() {

  return (
    <>
      <form action="/register">
        <div>
          <label>Email: </label>
          <input type="email" required />
        </div>

        <div>
          <label>Password: </label>
          <input type="password" required />
        </div>

        <div>
          <label>First name: </label>
          <input type="text" required />
        </div>

        <div>
          <label>Last name: </label>
          <input type="text" required />
        </div>

        <button>Sign up</button>
      </form>
    </>
  )
}

export default App
