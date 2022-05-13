import { createNativeStackNavigator } from "@react-navigation/native-stack";
import Login from "../screens/User/Login";
import Register from "../screens/User/Register";
import ForgetPassword from "../screens/User/ForgetPassword";

const Stack = createNativeStackNavigator();

interface PropsType {
  setLogin: (a: any) => void;
}

// Component _ LoginStack
const LoginStack: React.FC<PropsType> = ({ setLogin }) => {
  return (
    <Stack.Navigator
      screenOptions={{
        contentStyle: { backgroundColor: "white" },
        animation: "slide_from_right",
        headerShown: false,
      }}
    >
      {/* 로그인 */}
      <Stack.Screen name="Login">
        {(props) => <Login {...props} setLogin={setLogin} />}
      </Stack.Screen>
      {/* 회원가입 */}
      <Stack.Screen name="Register" component={Register} />
      {/* 비밀번호 찾기 */}
      <Stack.Screen name="ForgetPassword" component={ForgetPassword} />
    </Stack.Navigator>
  );
};

export default LoginStack;
