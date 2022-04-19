import AuthPOST from "../../components/user/authPOST";
import OauthDelete from "../../components/user/oauthDELETE";
import OauthPOST from "../../components/user/oauthPOST";
import UserDelete from "../../components/user/userDELETE";
import UserPATCH from "../../components/user/userPATCH";
import UserPOST from "../../components/user/userPOST";
import UserPasswordPOST from "../../components/user/userPasswordPOST";
import UserInfoGET from "../../components/user/userInfoGET";
import { Box, Paper, Stack } from "@mui/material";
import { styled } from "@mui/material/styles";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "left",
  color: theme.palette.text.secondary,
}));

export const User = () => {
  return (
    <>
      <Box sx={{ width: "100%" }}>
        <Stack spacing={2}>
          <Item>
            <AuthPOST />
          </Item>
          <Item>
            <UserPOST />
          </Item>
          <Item>
            <UserPATCH />
          </Item>
          <Item>
            <UserDelete />
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
          <Item>
            <UserInfoGET />
          </Item>
        </Stack>
      </Box>
    </>
  );
};
