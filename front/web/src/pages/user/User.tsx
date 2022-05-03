import UserLoginPOST from "components/user/userLoginPOST";
import OauthDelete from "components/user/oauthDELETE";
import OauthPOST from "components/user/oauthPOST";
import UserDelete from "components/user/userDELETE";
import UserPATCH from "components/user/userPATCH";
import UserRegisterPOST from "components/user/userRegisterPOST";
import UserPasswordPOST from "components/user/userPasswordPOST";
import UserGET from "components/user/userGET";
import { Box, Paper, Stack } from "@mui/material";
import { styled } from "@mui/material/styles";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "left",
  color: theme.palette.text.secondary,
  fontSize: 17,
}));

export const User = () => {
  return (
    <>
      <Box sx={{ width: "100%" }}>
        <Stack spacing={2}>
          <Item>
            <UserLoginPOST />
          </Item>
          <Item>
            <UserRegisterPOST />
          </Item>
          <Item>
            <UserPATCH />
          </Item>
          <Item>
            <UserDelete />
          </Item>
          <Item>
            <UserGET />
          </Item>
          <Item>
            <OauthPOST />
          </Item>
          <Item>
            <OauthDelete />
          </Item>
          <Item>
            <UserPasswordPOST />
          </Item>
        </Stack>
      </Box>
    </>
  );
};
