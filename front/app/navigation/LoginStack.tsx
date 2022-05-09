import { createNativeStackNavigator } from "@react-navigation/native-stack";
import Login from "../screens/User/Login";
import Register from "../screens/User/Register";
import ForgetPassword from "../screens/User/ForgetPassword";

interface PropsType {
  setLogin: (a: any) => void;
}

const Stack = createNativeStackNavigator();

const LoginStack: React.FC<PropsType> = ({ setLogin }) => {
  return (
    <Stack.Navigator
      screenOptions={{
        headerShown: false,
      }}
    >
      <Stack.Screen name="Login">
        {(props) => <Login {...props} setLogin={setLogin} />}
      </Stack.Screen>
      <Stack.Screen name="Register" component={Register} />
      <Stack.Screen name="ForgetPassword" component={ForgetPassword} />
    </Stack.Navigator>
  );
};

export default LoginStack;
